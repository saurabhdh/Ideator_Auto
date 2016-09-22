/*
 * Copyright (c) 2013-2015 MicroPact, Inc. All Rights Reserved.
 *
 * This software is the proprietary information of MicroPact, Inc.
 * Use is subject to license terms.
 */
package com.ideator.exception;

/**
 * @author MicroPact, Inc.
 */
@SuppressWarnings("serial")
public class PageException extends RuntimeException {

    /**
     * Requires failing message.
     * @param msg
     *            error message
     */
    public PageException(String msg) {
        super(msg);
    }
}
