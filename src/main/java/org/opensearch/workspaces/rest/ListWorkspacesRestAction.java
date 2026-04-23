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
import org.opensearch.rest.BaseRestHandler;
import org.opensearch.rest.RestRequest;
import org.opensearch.rest.action.RestToXContentListener;
import org.opensearch.transport.client.node.NodeClient;
import org.opensearch.workspaces.Constants;
import org.opensearch.workspaces.action.ListWorkspacesAction;
import org.opensearch.workspaces.action.ListWorkspacesRequest;

public class ListWorkspacesRestAction extends BaseRestHandler {
  @Override
  public String getName() {
    return "list_workspaces";
  }

  @Override
  public List<Route> routes() {
    return List.of(new Route(RestRequest.Method.GET, Constants.WORKSPACES_API_BASE));
  }

  @Override
  protected RestChannelConsumer prepareRequest(RestRequest request, NodeClient client)
      throws IOException {
    return channel ->
        client.executeLocally(
            ListWorkspacesAction.INSTANCE,
            new ListWorkspacesRequest(),
            new RestToXContentListener<>(channel));
  }
}
