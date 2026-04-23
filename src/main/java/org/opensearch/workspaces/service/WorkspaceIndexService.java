/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.workspaces.service;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.opensearch.action.get.GetRequest;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.action.search.SearchRequest;
import org.opensearch.action.support.WriteRequest;
import org.opensearch.client.Client;
import org.opensearch.cluster.service.ClusterService;
import org.opensearch.common.xcontent.XContentType;
import org.opensearch.core.action.ActionListener;
import org.opensearch.index.query.QueryBuilders;
import org.opensearch.search.builder.SearchSourceBuilder;
import org.opensearch.workspaces.model.Workspace;

/**
 * Service for workspace CRUD operations against .kibana index.
 * Workspaces are stored as saved objects with type "workspace".
 */
public class WorkspaceIndexService {
    private static final String KIBANA_INDEX = ".kibana";
    private final Client client;
    private final ClusterService clusterService;

    public WorkspaceIndexService(Client client, ClusterService clusterService) {
        this.client = client;
        this.clusterService = clusterService;
    }

    public void createWorkspace(String name, String description, List<String> features, String color, String owner,
                                ActionListener<Workspace> listener) {
        String id = UUID.randomUUID().toString().substring(0, 6);
        long now = Instant.now().toEpochMilli();
        Workspace workspace = new Workspace(id, name, description, owner, now, now, features, color);

        String docId = "workspace:" + id;
        IndexRequest request = new IndexRequest(KIBANA_INDEX)
            .id(docId)
            .source(workspace.toSource(), XContentType.JSON)
            .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);

        client.index(request, ActionListener.wrap(
            response -> listener.onResponse(workspace),
            listener::onFailure
        ));
    }

    public void getWorkspace(String id, ActionListener<Workspace> listener) {
        String docId = "workspace:" + id;
        client.get(new GetRequest(KIBANA_INDEX, docId), ActionListener.wrap(
            response -> {
                if (!response.isExists()) {
                    listener.onFailure(new IllegalArgumentException("Workspace [" + id + "] not found"));
                    return;
                }
                listener.onResponse(Workspace.fromSource(id, response.getSourceAsMap()));
            },
            listener::onFailure
        ));
    }

    public void listWorkspaces(ActionListener<List<Workspace>> listener) {
        SearchRequest searchRequest = new SearchRequest(KIBANA_INDEX)
            .source(new SearchSourceBuilder()
                .query(QueryBuilders.termQuery("type", "workspace"))
                .size(1000));

        client.search(searchRequest, ActionListener.wrap(
            response -> {
                List<Workspace> workspaces = Arrays.stream(response.getHits().getHits())
                    .map(hit -> {
                        String id = hit.getId().replace("workspace:", "");
                        return Workspace.fromSource(id, hit.getSourceAsMap());
                    })
                    .collect(Collectors.toList());
                listener.onResponse(workspaces);
            },
            listener::onFailure
        ));
    }

    public void updateWorkspace(String id, String name, String description, List<String> features, String color,
                                ActionListener<Workspace> listener) {
        getWorkspace(id, ActionListener.wrap(
            existing -> {
                long now = Instant.now().toEpochMilli();
                Workspace updated = new Workspace(id, name, description, existing.getOwner(),
                    existing.getCreatedAt(), now, features, color);

                String docId = "workspace:" + id;
                IndexRequest request = new IndexRequest(KIBANA_INDEX)
                    .id(docId)
                    .source(updated.toSource(), XContentType.JSON)
                    .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);

                client.index(request, ActionListener.wrap(
                    response -> listener.onResponse(updated),
                    listener::onFailure
                ));
            },
            listener::onFailure
        ));
    }

    public void deleteWorkspace(String id, ActionListener<Boolean> listener) {
        String docId = "workspace:" + id;
        client.delete(new org.opensearch.action.delete.DeleteRequest(KIBANA_INDEX, docId)
            .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE),
            ActionListener.wrap(
                response -> listener.onResponse(true),
                listener::onFailure
            ));
    }
}
