package org.opensearch.workspaces.action;
import org.opensearch.action.support.ActionFilters;
import org.opensearch.action.support.HandledTransportAction;
import org.opensearch.common.inject.Inject;
import org.opensearch.core.action.ActionListener;
import org.opensearch.tasks.Task;
import org.opensearch.transport.TransportService;
import org.opensearch.workspaces.service.WorkspaceIndexService;
public class DeleteWorkspaceTransportAction extends HandledTransportAction<DeleteWorkspaceRequest, DeleteWorkspaceResponse> {
    private final WorkspaceIndexService service;
    @Inject
    public DeleteWorkspaceTransportAction(TransportService transportService, ActionFilters actionFilters,
                                          WorkspaceIndexService service) {
        super(DeleteWorkspaceAction.NAME, transportService, actionFilters, DeleteWorkspaceRequest::new);
        this.service = service;
    }
    @Override
    protected void doExecute(Task task, DeleteWorkspaceRequest request, ActionListener<DeleteWorkspaceResponse> listener) {
        service.deleteWorkspace(request.getWorkspaceId(), ActionListener.wrap(
            ok -> listener.onResponse(new DeleteWorkspaceResponse(ok)), listener::onFailure));
    }
}
