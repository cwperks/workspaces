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
import org.opensearch.commons.ConfigConstants;
import org.opensearch.commons.authuser.User;
import org.opensearch.core.action.ActionListener;
import org.opensearch.tasks.Task;
import org.opensearch.threadpool.ThreadPool;
import org.opensearch.transport.TransportService;
import org.opensearch.workspaces.service.WorkspaceIndexService;

public class CreateWorkspaceTransportAction
    extends HandledTransportAction<CreateWorkspaceRequest, CreateWorkspaceResponse> {
  private final WorkspaceIndexService service;
  private final ThreadPool threadPool;

  @Inject
  public CreateWorkspaceTransportAction(
      TransportService transportService,
      ActionFilters actionFilters,
      WorkspaceIndexService service) {
    super(CreateWorkspaceAction.NAME, transportService, actionFilters, CreateWorkspaceRequest::new);
    this.service = service;
    this.threadPool = transportService.getThreadPool();
  }

  @Override
  protected void doExecute(
      Task task, CreateWorkspaceRequest request, ActionListener<CreateWorkspaceResponse> listener) {
    String owner = "unknown";
    String userString =
        threadPool
            .getThreadContext()
            .getTransient(ConfigConstants.OPENSEARCH_SECURITY_USER_INFO_THREAD_CONTEXT);
    if (userString != null) {
      owner = User.parse(userString).getName();
    }
    service.createWorkspace(
        request.getName(),
        request.getDescription(),
        request.getFeatures(),
        request.getColor(),
        owner,
        ActionListener.wrap(
            ws -> listener.onResponse(new CreateWorkspaceResponse(ws)), listener::onFailure));
  }
}
