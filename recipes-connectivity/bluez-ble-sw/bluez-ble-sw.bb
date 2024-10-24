SUMMARY = "BlueZ BLE Applications"
DESCRIPTION = "BlueZ BLE UART and DFU Applications"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://readme;md5=ab833928dd4d2429b09fc5d3497d811c"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "git://github.com/linux4microchip/ble_bluez_hci_apps.git;protocol=https;branch=master"
SRCREV	= "b79d1c0938ce6f853ef12bf611e4a522ed6b2ffe"

PV = "1.0+git${SRCPV}"
S = "${WORKDIR}/git"

DEPENDS = "bluez5"

inherit cmake pkgconfig

EXTRA_OECMAKE += "-DCMAKE_C_FLAGS='${CMAKE_C_FLAGS} -I${STAGING_INCDIR} -I${STAGING_INCDIR}/bluez5_utils -I${STAGING_INCDIR}/bluez5_utils/src'"
LDFLAGS += "-L${STAGING_LIBDIR}/bluez5_utils/lib"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/apps/ble_uart_app/ble-uart-bluez ${D}${bindir}/
    install -m 0755 ${B}/apps/dfu_app/dfu-bluez ${D}${bindir}/
}

FILES:${PN} = "${bindir}/*"
