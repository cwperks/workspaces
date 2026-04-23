/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.workspaces.action;

import org.opensearch.action.ActionType;

public class GetWorkspaceAction extends ActionType<GetWorkspaceResponse> {
  public static final String NAME = "workspaces:workspace/get";
  public static final GetWorkspaceAction INSTANCE = new GetWorkspaceAction();

  private GetWorkspaceAction() {
    super(NAME, GetWorkspaceResponse::new);
  }
}
