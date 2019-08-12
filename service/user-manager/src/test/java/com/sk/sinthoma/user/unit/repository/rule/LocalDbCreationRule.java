/**
 * LocalDbCreationRule.java
 * user-manager
 * Copyright 2019 Shishir Kumar
 * 
 * Licensed under the GNU Lesser General Public License v3.0
 * Permissions of this license are conditioned on making available complete 
 * source code of licensed works and modifications under the same license 
 * or the GNU GPLv3. Copyright and license notices must be preserved. 
 * 
 * Contributors provide an express grant of patent rights. However, a larger 
 * work using the licensed work through interfaces provided by the licensed 
 * work may be distributed under different terms and without source code for 
 * the larger work.
 */
package com.sk.sinthoma.user.unit.repository.rule;

import org.junit.rules.ExternalResource;

import com.amazonaws.services.dynamodbv2.local.main.ServerRunner;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;

public class LocalDbCreationRule extends ExternalResource {

    protected DynamoDBProxyServer server;

    public LocalDbCreationRule() {
	System.setProperty("sqlite4java.library.path", "native-libs");
    }

    @Override
    protected void before() throws Exception {
	final String port = "8000";
	server = ServerRunner.createServerFromCommandLineArgs(new String[] { "-inMemory", "-port", port });
	server.start();
    }

    @Override
    protected void after() {
	stopUnchecked(server);
    }

    protected void stopUnchecked(DynamoDBProxyServer dynamoDbServer) {
	try {
	    dynamoDbServer.stop();
	} catch (final Exception e) {
	    throw new RuntimeException(e);
	}
    }

}