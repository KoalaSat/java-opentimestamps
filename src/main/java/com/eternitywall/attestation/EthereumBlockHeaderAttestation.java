package com.eternitywall.attestation; /**
 * Created by luca on 25/02/2017.
 */

import com.eternitywall.StreamDeserializationContext;
import com.eternitywall.StreamSerializationContext;

import java.util.logging.Logger;

/**
 * Ethereum Block Header Attestation.
 *
 * @extends com.eternitywall.attestation.TimeAttestation
 */
public class EthereumBlockHeaderAttestation extends TimeAttestation {

    public static byte[] _TAG = {(byte) 0x30, (byte) 0xfe, (byte) 0x80, (byte) 0x87, (byte) 0xb5, (byte) 0xc7, (byte) 0xea, (byte) 0xd7};
    private static Logger log = Logger.getLogger(EthereumBlockHeaderAttestation.class.getName());

    @Override
    public byte[] _TAG() {
        return EthereumBlockHeaderAttestation._TAG;
    }

    private int height = 0;

    public int getHeight() {
        return height;
    }

    EthereumBlockHeaderAttestation(int height_) {
        super();
        this.height = height_;
    }

    public static EthereumBlockHeaderAttestation deserialize(StreamDeserializationContext ctxPayload) {
        int height = ctxPayload.readVaruint();
        return new EthereumBlockHeaderAttestation(height);
    }

    @Override
    public void serializePayload(StreamSerializationContext ctx) {
        ctx.writeVaruint(this.height);
    }

    public String toString() {
        return "EthereumBlockHeaderAttestation(" + this.height + ")";
    }


    @Override
    public int compareTo(TimeAttestation o) {
        EthereumBlockHeaderAttestation ob = (EthereumBlockHeaderAttestation) o;
        return this.height - ob.height;
    }
}