/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.workspaces.rest;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.opensearch.rest.BaseRestHandler;
import org.opensearch.rest.RestRequest;
import org.opensearch.rest.action.RestToXContentListener;
import org.opensearch.transport.client.node.NodeClient;
import org.opensearch.workspaces.Constants;
import org.opensearch.workspaces.action.CreateWorkspaceAction;
import org.opensearch.workspaces.action.CreateWorkspaceRequest;

public class CreateWorkspaceRestAction extends BaseRestHandler {
  @Override
  public String getName() {
    return "create_workspace";
  }

  @Override
  public List<Route> routes() {
    return List.of(new Route(RestRequest.Method.POST, Constants.WORKSPACES_API_BASE));
  }

  @Override
  @SuppressWarnings("unchecked")
  protected RestChannelConsumer prepareRequest(RestRequest request, NodeClient client)
      throws IOException {
    Map<String, Object> body = request.contentParser().map();
    CreateWorkspaceRequest actionRequest =
        new CreateWorkspaceRequest(
            (String) body.get("name"),
            (String) body.getOrDefault("description", ""),
            (List<String>) body.getOrDefault("features", List.of()),
            (String) body.getOrDefault("color", ""));
    return channel ->
        client.executeLocally(
            CreateWorkspaceAction.INSTANCE, actionRequest, new RestToXContentListener<>(channel));
  }
}
