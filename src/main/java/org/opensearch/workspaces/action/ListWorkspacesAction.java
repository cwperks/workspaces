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

public class ListWorkspacesAction extends ActionType<ListWorkspacesResponse> {
  public static final String NAME = "workspaces:workspace/list";
  public static final ListWorkspacesAction INSTANCE = new ListWorkspacesAction();

  private ListWorkspacesAction() {
    super(NAME, ListWorkspacesResponse::new);
  }
}
