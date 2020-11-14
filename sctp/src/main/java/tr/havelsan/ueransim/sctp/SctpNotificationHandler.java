/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.sctp;

import com.sun.nio.sctp.AbstractNotificationHandler;
import com.sun.nio.sctp.AssociationChangeNotification;
import com.sun.nio.sctp.HandlerResult;
import com.sun.nio.sctp.ShutdownNotification;

import java.io.PrintStream;

class SctpNotificationHandler extends AbstractNotificationHandler<PrintStream> {

    private final ISctpAssociationHandler sctpAssociationHandler;

    public SctpNotificationHandler(ISctpAssociationHandler sctpAssociationHandler) {
        this.sctpAssociationHandler = sctpAssociationHandler;
    }

    @Override
    public HandlerResult handleNotification(AssociationChangeNotification notification, PrintStream attachment) {
        if (notification.event() == AssociationChangeNotification.AssocChangeEvent.COMM_UP) {
            sctpAssociationHandler.onSetup(new SctpAssociation(notification.association().associationID(),
                    notification.association().maxInboundStreams(),
                    notification.association().maxOutboundStreams()));
            return HandlerResult.CONTINUE;
        }
        throw new RuntimeException("SCTP association failure: " + notification.event());
    }

    @Override
    public HandlerResult handleNotification(ShutdownNotification notification, PrintStream attachment) {
        sctpAssociationHandler.onShutdown();
        return HandlerResult.RETURN;
    }
}