SUMMARY = "Download videos from YouTube (and more sites)"
DESCRIPTION = "youtube-dl is a small command-line program to download videos \
from YouTube.com and a few more sites. It requires the Python interpreter \
(2.6, 2.7, or 3.2+), and it is not platform specific"
HOMEPAGE = "https://youtube-dl.org"
SECTION = "devel/python"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9936da26f04f6454c738c5f4fda24799"

DEPENDS = "libxml2 bash-completion"

PV = "2020.11.01"

SRC_URI = "https://source.netsyms.com/Mirrors/l1ving_youtube-dl/archive/${PV}.tar.gz"

SRC_URI[md5sum] = "22020bb675e4c84e35149ad7abad09b1"
SRC_URI[sha256sum] = "8aa3a6fd729749b931be0549bc384e4332e1f97581fec932eaf8d55fef5b221e"

S = "${WORKDIR}/l1ving_youtube-dl"

inherit ${@bb.utils.contains("PYTHONEXACTVERSION", "python3", "setuptools3", "setuptools", d)}

EXTRA_OEMAKE = "PYTHON=${PYTHON}"

do_compile_prepend() {
    oe_runmake lazy-extractors youtube-dl.bash-completion
}

do_install_append() {
    mv ${D}${datadir}${sysconfdir} ${D}${sysconfdir}
    install -m 0755 -d ${D}${sysconfdir}/bash_completion.d
    install -m 0644 youtube-dl.bash-completion ${D}${sysconfdir}/bash_completion.d
}

RDEPENDS_${PN} = " \
	${PYTHONNAMEONLY}-email \
	${@bb.utils.contains("PYTHONEXACTVERSION", "python3", "", " \
	python-gdata \
	python-subprocess", d)} \
	${PYTHONNAMEONLY}-unixadmin \
	${PYTHONNAMEONLY}-ctypes \
	${PYTHONNAMEONLY}-argparse \
	${PYTHONNAMEONLY}-html \
	"

RDEPENDS_{PN}-src = "${PN}"
FILES_${PN}-src = " \
    ${libdir}/${PYTHON_DIR}/site-packages/*/*.py \
    ${libdir}/${PYTHON_DIR}/site-packages/*/*/*.py \
    ${libdir}/${PYTHON_DIR}/site-packages/*/*/*/*.py \
    ${libdir}/${PYTHON_DIR}/site-packages/*/*/*/*/*.py \
    ${datadir}${sysconfdir}/* \
    "

FILES_${PN} += "${sysconfdir}"
