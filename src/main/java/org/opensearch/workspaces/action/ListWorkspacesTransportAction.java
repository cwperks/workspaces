package org.opensearch.workspaces.action;
import org.opensearch.action.support.ActionFilters;
import org.opensearch.action.support.HandledTransportAction;
import org.opensearch.common.inject.Inject;
import org.opensearch.core.action.ActionListener;
import org.opensearch.tasks.Task;
import org.opensearch.transport.TransportService;
import org.opensearch.workspaces.service.WorkspaceIndexService;
public class ListWorkspacesTransportAction extends HandledTransportAction<ListWorkspacesRequest, ListWorkspacesResponse> {
    private final WorkspaceIndexService service;
    @Inject
    public ListWorkspacesTransportAction(TransportService transportService, ActionFilters actionFilters,
                                         WorkspaceIndexService service) {
        super(ListWorkspacesAction.NAME, transportService, actionFilters, ListWorkspacesRequest::new);
        this.service = service;
    }
    @Override
    protected void doExecute(Task task, ListWorkspacesRequest request, ActionListener<ListWorkspacesResponse> listener) {
        service.listWorkspaces(ActionListener.wrap(
            list -> listener.onResponse(new ListWorkspacesResponse(list)), listener::onFailure));
    }
}
