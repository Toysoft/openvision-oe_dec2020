# package is machine specific
PACKAGE_ARCH := "${MACHINE_ARCH}"

FILESEXTRAPATHS_prepend := "${THISDIR}/qtbase:"
SRC_URI_append += "file://mkspecs-fix-build-with-gcc9.patch"
