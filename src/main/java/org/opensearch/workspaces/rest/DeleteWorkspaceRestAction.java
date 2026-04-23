package org.opensearch.workspaces.rest;
import java.io.IOException;
import java.util.List;
import org.opensearch.rest.BaseRestHandler;
import org.opensearch.rest.RestRequest;
import org.opensearch.rest.action.RestToXContentListener;
import org.opensearch.transport.client.node.NodeClient;
import org.opensearch.workspaces.Constants;
import org.opensearch.workspaces.action.DeleteWorkspaceAction;
import org.opensearch.workspaces.action.DeleteWorkspaceRequest;
public class DeleteWorkspaceRestAction extends BaseRestHandler {
    @Override public String getName() { return "delete_workspace"; }
    @Override public List<Route> routes() { return List.of(new Route(RestRequest.Method.DELETE, Constants.WORKSPACES_API_BASE + "/{id}")); }
    @Override
    protected RestChannelConsumer prepareRequest(RestRequest request, NodeClient client) throws IOException {
        return channel -> client.executeLocally(DeleteWorkspaceAction.INSTANCE,
            new DeleteWorkspaceRequest(request.param("id")), new RestToXContentListener<>(channel));
    }
}
