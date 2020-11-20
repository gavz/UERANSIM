package tr.havelsan.ueransim.app.ue.sm;

import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.nas.impl.enums.EPduSessionIdentity;
import tr.havelsan.ueransim.nas.impl.enums.ESmCause;
import tr.havelsan.ueransim.nas.impl.ies.IE5gSmCause;
import tr.havelsan.ueransim.nas.impl.messages.PduSessionReleaseCommand;
import tr.havelsan.ueransim.nas.impl.messages.PduSessionReleaseReject;
import tr.havelsan.ueransim.nas.impl.messages.PduSessionReleaseRequest;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.console.Log;

public class SmPduSessionRelease {

    public static void sendReleaseRequest(UeSimContext ctx, ESmCause value, EPduSessionIdentity pduSessionId) {
        Log.funcIn("Sending PDU Session Release Request");

        var procedureTransactionId = SmPduSessionManagement.allocateProcedureTransactionId(ctx);
        if (procedureTransactionId == null) {
            Log.error(Tag.PROC, "PDU Session Release Request could not send: PTI could not be allocated");
            Log.funcOut();
            return;
        }

        var request = new PduSessionReleaseRequest();
        request.pduSessionId = pduSessionId;
        request.pti = procedureTransactionId;
        request.smCause = new IE5gSmCause(value);

        ctx.ueTimers.t3582.start();

        SessionManagement.sendSm(ctx, pduSessionId, request);

        Log.funcOut();
    }

    public static void receiveReleaseAccept(UeSimContext ctx, PduSessionReleaseCommand message) {

    }

    public static void receiveReleaseReject(UeSimContext ctx, PduSessionReleaseReject message) {
        Log.funcIn("Handling: PDU Session Release Reject");

        ctx.ueTimers.t3582.stop();

        SmPduSessionManagement.releaseProcedureTransactionId(ctx, message.pti);

        var cause = message.smCause.value;
        if (!cause.equals(ESmCause.INVALID_PDU_SESSION_IDENTITY) && !cause.equals(ESmCause.INVALID_PTI_VALUE)) {
            Log.error(Tag.PROCEDURE_RESULT, "PDU Session Release is failed: PDU session is not released (%s)", cause);
        } else {
            Log.error(Tag.PROCEDURE_RESULT, "PDU Session Release is failed: (%s)", cause);
        }

        Log.funcOut();
    }
}
