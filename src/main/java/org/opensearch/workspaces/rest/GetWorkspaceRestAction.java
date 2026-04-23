package org.opensearch.workspaces.rest;
import java.io.IOException;
import java.util.List;
import org.opensearch.rest.BaseRestHandler;
import org.opensearch.rest.RestRequest;
import org.opensearch.rest.action.RestToXContentListener;
import org.opensearch.transport.client.node.NodeClient;
import org.opensearch.workspaces.Constants;
import org.opensearch.workspaces.action.GetWorkspaceAction;
import org.opensearch.workspaces.action.GetWorkspaceRequest;
public class GetWorkspaceRestAction extends BaseRestHandler {
    @Override public String getName() { return "get_workspace"; }
    @Override public List<Route> routes() { return List.of(new Route(RestRequest.Method.GET, Constants.WORKSPACES_API_BASE + "/{id}")); }
    @Override
    protected RestChannelConsumer prepareRequest(RestRequest request, NodeClient client) throws IOException {
        return channel -> client.executeLocally(GetWorkspaceAction.INSTANCE,
            new GetWorkspaceRequest(request.param("id")), new RestToXContentListener<>(channel));
    }
}
