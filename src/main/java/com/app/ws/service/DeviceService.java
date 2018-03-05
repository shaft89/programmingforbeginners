/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.ws.service;

import com.app.ws.shared.dto.DeviceDTO;

/**
 *
 * @author SBo
 */
public interface DeviceService {
    DeviceDTO getDevice(long id);
    DeviceDTO createDevice(DeviceDTO device);
}
