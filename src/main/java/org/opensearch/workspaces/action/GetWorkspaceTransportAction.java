package org.opensearch.workspaces.action;
import org.opensearch.action.support.ActionFilters;
import org.opensearch.action.support.HandledTransportAction;
import org.opensearch.common.inject.Inject;
import org.opensearch.core.action.ActionListener;
import org.opensearch.tasks.Task;
import org.opensearch.transport.TransportService;
import org.opensearch.workspaces.service.WorkspaceIndexService;
public class GetWorkspaceTransportAction extends HandledTransportAction<GetWorkspaceRequest, GetWorkspaceResponse> {
    private final WorkspaceIndexService service;
    @Inject
    public GetWorkspaceTransportAction(TransportService transportService, ActionFilters actionFilters,
                                       WorkspaceIndexService service) {
        super(GetWorkspaceAction.NAME, transportService, actionFilters, GetWorkspaceRequest::new);
        this.service = service;
    }
    @Override
    protected void doExecute(Task task, GetWorkspaceRequest request, ActionListener<GetWorkspaceResponse> listener) {
        service.getWorkspace(request.getWorkspaceId(), ActionListener.wrap(
            ws -> listener.onResponse(new GetWorkspaceResponse(ws)), listener::onFailure));
    }
}
