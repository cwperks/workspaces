/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.workspaces;

public final class Constants {
  private Constants() {}

  public static final String WORKSPACES_API_BASE = "/_plugins/_workspaces";
  public static final String WORKSPACE_RESOURCE_TYPE = "workspace";

  // Access levels for workspace collaborators
  public static final String WORKSPACE_READ = "workspace_read";
  public static final String WORKSPACE_WRITE = "workspace_write";
  public static final String WORKSPACE_ADMIN = "workspace_admin";
}
