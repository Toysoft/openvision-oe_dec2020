FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " ${@bb.utils.contains("MACHINE_FEATURES", "oldkernel", "file://iputils_oldkernel.patch", "", d)}"
