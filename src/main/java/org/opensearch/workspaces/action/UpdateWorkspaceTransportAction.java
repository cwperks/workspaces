/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.workspaces.action;

import org.opensearch.action.support.ActionFilters;
import org.opensearch.action.support.HandledTransportAction;
import org.opensearch.common.inject.Inject;
import org.opensearch.core.action.ActionListener;
import org.opensearch.tasks.Task;
import org.opensearch.transport.TransportService;
import org.opensearch.workspaces.service.WorkspaceIndexService;

public class UpdateWorkspaceTransportAction
    extends HandledTransportAction<UpdateWorkspaceRequest, UpdateWorkspaceResponse> {
  private final WorkspaceIndexService service;

  @Inject
  public UpdateWorkspaceTransportAction(
      TransportService transportService,
      ActionFilters actionFilters,
      WorkspaceIndexService service) {
    super(UpdateWorkspaceAction.NAME, transportService, actionFilters, UpdateWorkspaceRequest::new);
    this.service = service;
  }

  @Override
  protected void doExecute(
      Task task, UpdateWorkspaceRequest request, ActionListener<UpdateWorkspaceResponse> listener) {
    service.updateWorkspace(
        request.getWorkspaceId(),
        request.getName(),
        request.getDescription(),
        request.getFeatures(),
        request.getColor(),
        ActionListener.wrap(
            ws -> listener.onResponse(new UpdateWorkspaceResponse(ws)), listener::onFailure));
  }
}
