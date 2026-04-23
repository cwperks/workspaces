/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.workspaces;

import java.util.Set;
import org.opensearch.security.spi.resources.ResourceProvider;
import org.opensearch.security.spi.resources.ResourceSharingExtension;
import org.opensearch.security.spi.resources.client.ResourceSharingClient;

public class WorkspacesResourceSharingExtension implements ResourceSharingExtension {

    @Override
    public Set<ResourceProvider> getResourceProviders() {
        return Set.of(
            new ResourceProvider() {
                @Override
                public String resourceType() {
                    return Constants.WORKSPACE_RESOURCE_TYPE;
                }

                @Override
                public String resourceIndexName() {
                    return ".kibana*";
                }

                @Override
                public String typeField() {
                    return "type";
                }
            }
        );
    }

    @Override
    public void assignResourceSharingClient(ResourceSharingClient resourceSharingClient) {
        // Workspaces relies on security's REST APIs for sharing
    }
}
