/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common;

import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.ue.nas.NasTimer;

public class UeTimers {
    public final UeSimContext ctx;

    public final NasTimer t3346; /* MM - ... */
    public final NasTimer t3396; /* SM - ... */

    public final NasTimer t3444; /* MM - ... */
    public final NasTimer t3445; /* MM - ... */

    public final NasTimer t3502; /* MM - ... */
    public final NasTimer t3510; /* MM - Registration Request transmission timer */
    public final NasTimer t3511; /* MM - ... */
    public final NasTimer t3512; /* MM - Periodic registration update timer */
    public final NasTimer t3516; /* MM - 5G AKA - RAND and RES* storing timer */
    public final NasTimer t3517; /* MM - Service Request transmission timer */
    public final NasTimer t3519; /* MM - Transmission with fresh SUCI timer */
    public final NasTimer t3520; /* MM - ... */
    public final NasTimer t3521; /* MM - De-registration transmission timer for not switch off */
    public final NasTimer t3525; /* MM - ... */
    public final NasTimer t3540; /* MM - ... */

    public final NasTimer t3580; /* SM - ... */
    public final NasTimer t3581; /* SM - ... */
    public final NasTimer t3582; /* SM - ... */
    public final NasTimer t3583; /* SM - ... */
    public final NasTimer t3584; /* SM - ... */
    public final NasTimer t3585; /* SM - ... */

    public UeTimers(UeSimContext ctx) {
        this.ctx = ctx;

        this.t3346 = new NasTimer(ctx, 3346, true, Integer.MAX_VALUE);
        this.t3396 = new NasTimer(ctx, 3396, false, Integer.MAX_VALUE);
        this.t3444 = new NasTimer(ctx, 3444, true, 12 * 60 * 60);
        this.t3445 = new NasTimer(ctx, 3445, true, 12 * 60 * 60);
        this.t3502 = new NasTimer(ctx, 3502, true, 12 * 60);
        this.t3510 = new NasTimer(ctx, 3510, true, 15);
        this.t3511 = new NasTimer(ctx, 3511, true, 10);
        this.t3512 = new NasTimer(ctx, 3512, true, 54 * 60);
        this.t3516 = new NasTimer(ctx, 3516, true, 30);
        this.t3517 = new NasTimer(ctx, 3517, true, 15);
        this.t3519 = new NasTimer(ctx, 3519, true, 60);
        this.t3520 = new NasTimer(ctx, 3520, true, 15);
        this.t3521 = new NasTimer(ctx, 3521, true, 15);
        this.t3525 = new NasTimer(ctx, 3525, true, 60);
        this.t3540 = new NasTimer(ctx, 3540, true, 10);
        this.t3580 = new NasTimer(ctx, 3580, false, 16);
        this.t3581 = new NasTimer(ctx, 3581, false, 16);
        this.t3582 = new NasTimer(ctx, 3582, false, 16);
        this.t3583 = new NasTimer(ctx, 3583, false, 60);
        this.t3584 = new NasTimer(ctx, 3584, false, Integer.MAX_VALUE);
        this.t3585 = new NasTimer(ctx, 3585, false, Integer.MAX_VALUE);
    }
}
