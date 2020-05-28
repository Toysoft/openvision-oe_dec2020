LICENSE = "CLOSED"
require conf/license/license-close.inc

inherit allarch gitpkgv

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"

SRC_URI = "git://github.com/ciefp/ciefpsettings-enigma2.git;protocol=git"

S = "${WORKDIR}/git"

FILES_${PN} = "${sysconfdir}/enigma2/"

do_install () {
    install -d ${D}${sysconfdir}/enigma2
    cp -r ${S}/${SETTINGS_FILENAME}/* ${D}${sysconfdir}/enigma2
}

do_prepare_recipe_sysroot[noexec] = "1"
do_populate_sysroot[noexec] = "1"
do_package_qa[noexec] = "1"