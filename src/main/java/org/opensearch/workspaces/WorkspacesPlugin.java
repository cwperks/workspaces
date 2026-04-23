/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.workspaces;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import org.opensearch.action.ActionRequest;
import org.opensearch.cluster.metadata.IndexNameExpressionResolver;
import org.opensearch.cluster.node.DiscoveryNodes;
import org.opensearch.cluster.service.ClusterService;
import org.opensearch.common.settings.ClusterSettings;
import org.opensearch.common.settings.IndexScopedSettings;
import org.opensearch.common.settings.Settings;
import org.opensearch.common.settings.SettingsFilter;
import org.opensearch.core.action.ActionResponse;
import org.opensearch.core.common.io.stream.NamedWriteableRegistry;
import org.opensearch.core.xcontent.NamedXContentRegistry;
import org.opensearch.env.Environment;
import org.opensearch.env.NodeEnvironment;
import org.opensearch.identity.PluginSubject;
import org.opensearch.plugins.ActionPlugin;
import org.opensearch.plugins.IdentityAwarePlugin;
import org.opensearch.plugins.Plugin;
import org.opensearch.repositories.RepositoriesService;
import org.opensearch.rest.RestController;
import org.opensearch.rest.RestHandler;
import org.opensearch.script.ScriptService;
import org.opensearch.threadpool.ThreadPool;
import org.opensearch.transport.client.Client;
import org.opensearch.watcher.ResourceWatcherService;
import org.opensearch.workspaces.action.*;
import org.opensearch.workspaces.rest.*;
import org.opensearch.workspaces.service.PluginClient;
import org.opensearch.workspaces.service.WorkspaceIndexService;

public class WorkspacesPlugin extends Plugin implements ActionPlugin, IdentityAwarePlugin {

    private PluginClient pluginClient;

    @Override
    public Collection<Object> createComponents(
        Client client,
        ClusterService clusterService,
        ThreadPool threadPool,
        ResourceWatcherService resourceWatcherService,
        ScriptService scriptService,
        NamedXContentRegistry xContentRegistry,
        Environment environment,
        NodeEnvironment nodeEnvironment,
        NamedWriteableRegistry namedWriteableRegistry,
        IndexNameExpressionResolver indexNameExpressionResolver,
        Supplier<RepositoriesService> repositoriesServiceSupplier
    ) {
        this.pluginClient = new PluginClient(client);
        WorkspaceIndexService workspaceIndexService = new WorkspaceIndexService(pluginClient, clusterService);
        return List.of(pluginClient, workspaceIndexService);
    }

    @Override
    public List<RestHandler> getRestHandlers(
        Settings settings,
        RestController restController,
        ClusterSettings clusterSettings,
        IndexScopedSettings indexScopedSettings,
        SettingsFilter settingsFilter,
        IndexNameExpressionResolver indexNameExpressionResolver,
        Supplier<DiscoveryNodes> nodesInCluster
    ) {
        return List.of(
            new CreateWorkspaceRestAction(),
            new GetWorkspaceRestAction(),
            new ListWorkspacesRestAction(),
            new UpdateWorkspaceRestAction(),
            new DeleteWorkspaceRestAction()
        );
    }

    @Override
    public List<ActionHandler<? extends ActionRequest, ? extends ActionResponse>> getActions() {
        return List.of(
            new ActionHandler<>(CreateWorkspaceAction.INSTANCE, CreateWorkspaceTransportAction.class),
            new ActionHandler<>(GetWorkspaceAction.INSTANCE, GetWorkspaceTransportAction.class),
            new ActionHandler<>(ListWorkspacesAction.INSTANCE, ListWorkspacesTransportAction.class),
            new ActionHandler<>(UpdateWorkspaceAction.INSTANCE, UpdateWorkspaceTransportAction.class),
            new ActionHandler<>(DeleteWorkspaceAction.INSTANCE, DeleteWorkspaceTransportAction.class)
        );
    }

    @Override
    public void assignSubject(PluginSubject pluginSubject) {
        this.pluginClient.setSubject(pluginSubject);
    }
}
