package com.example.opcua;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.springframework.stereotype.Service;

@Service
public class OpcUaService {

    private final OpcUaClient client;

    public OpcUaService() throws Exception {
        this.client = OpcUaClient.create("opc.tcp://localhost:4840");
        client.connect().get();
    }

    public void writeData(String nodeId, Object value) throws Exception {
        NodeId node = new NodeId(2, nodeId);
        client.writeValue(node, DataValue.valueOnly(new Variant(value))).get();
    }
}
