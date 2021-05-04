suite = {
  "mxversion" : "5.175.4",
  "name" : "dsp56k",
  "versionConflictResolution" : "latest",

  "javac.lint.overrides" : "none",

  "imports" : {
    "suites" : [
      {
        "name" : "vmx86",
        "version" : "5d8a4815ca98b85deba21cb370ccbe672bd54692",
        "urls" : [
          {"url" : "https://github.com/pekd/vmx86", "kind" : "git"},
        ]
      },
    ],
  },

  "licenses" : {
    "GPLv3" : {
      "name" : "GNU General Public License, version 3",
      "url" : "https://opensource.org/licenses/GPL-3.0",
    }
  },

  "defaultLicense" : "GPLv3",

  "projects" : {

    "org.graalvm.vm.trcview.arch.dsp56k" : {
      "subDir" : "projects",
      "sourceDirs" : ["src"],
      "dependencies" : [
        "vmx86:TRCVIEW_MINIMAL",
      ],
      "javaCompliance" : "1.8+",
      "workingSets" : "vmx86",
      "license" : "GPLv3",
    },

    "org.graalvm.vm.trcview.arch.dsp56k.test" : {
      "subDir" : "projects",
      "sourceDirs" : ["src"],
      "dependencies" : [
        "org.graalvm.vm.trcview.arch.dsp56k",
        "vmx86:VMX86_TRCVIEW",
        "mx:JUNIT",
      ],
      "javaCompliance" : "1.8+",
      "workingSets" : "vmx86",
      "testProject" : "True",
      "license" : "GPLv3",
    },

  },

  "distributions" : {
    "TRCVIEW_DSP56K_PLUGIN" : {
      "path" : "build/dsp56k.jar",
      "sourcesPath" : "build/dsp56k.src.zip",
      "subDir" : "dsp56k",
      "dependencies" : [
        "org.graalvm.vm.trcview.arch.dsp56k",
      ],
      "distDependencies" : [
        "vmx86:TRCVIEW_MINIMAL",
      ],
      "license" : "GPLv3",
    },

    "TRCVIEW_DSP56K_PLUGIN_TEST" : {
      "path" : "build/dsp56k_test.jar",
      "sourcesPath" : "build/dsp56k_test.src.zip",
      "subDir" : "dsp56k",
      "dependencies" : [
        "org.graalvm.vm.trcview.arch.dsp56k.test",
      ],
      "exclude" : [
        "mx:JUNIT"
      ],
      "distDependencies" : [
        "vmx86:VMX86_TRCVIEW",
      ],
      "license" : "GPLv3",
    },

    "TRCVIEW_DSP56K" : {
      "path" : "build/trcview.jar",
      "sourcesPath" : "build/trcview.src.zip",
      "subDir" : "dsp56k",
      "mainClass" : "org.graalvm.vm.trcview.ui.MainWindow",
      "dependencies" : [
        "org.graalvm.vm.trcview.arch.dsp56k",
      ],
      "license" : "GPLv3",
    }
  }
}
