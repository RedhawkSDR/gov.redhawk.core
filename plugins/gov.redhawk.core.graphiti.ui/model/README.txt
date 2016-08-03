###############################################################################
# This file is protected by Copyright.
# Please refer to the COPYRIGHT file distributed with this source distribution.
#
# This file is part of REDHAWK IDE.
#
# All rights reserved. This program and the accompanying materials are made available under
# the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
###############################################################################

The ecore and genmodel files from bundle org.eclipse.graphiti.mm 0.12.0.v20150603-0807 are included here. Without them,
it is difficult to reference the Graphiti model. You have to import the plugin to your workspace, right-click and add
the Xtext nature (Configure -> Add Xtext Nature). Without this, Xtext isn't able to index Graphiti's model and won't
recognize things we reference from it.

For a discussion, see Ed Merks comments at https://www.eclipse.org/forums/index.php/t/367588/.
