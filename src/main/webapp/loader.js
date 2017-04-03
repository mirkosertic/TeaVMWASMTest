var TeaVM = function() {

    function TeaVM() {
        this.instance = null;
        this.module = null;
        this.memory = null;
        this.memoryArray = null;
    }

    TeaVM.prototype.run = function() {
         load(this, function() {this.instance.exports.main();}.bind(this))
    };

    function load(teavm, callback) {
        var xhr = new XMLHttpRequest()
        xhr.responseType = "arraybuffer"
        xhr.open("GET", "teavm-wasm/classes.wasm")
        xhr.onload = function () {
            var response = xhr.response;
            if (!response) {
                return;
            }

            var importObj = {
                log: {
                    log_int: function (int) {
                        console.log("Log int : " + int)
                    },
                    log_float: function (f) {
                        console.log("Log float : " + f)
                    },
                    log_double: function (du) {
                        console.log("Log double : " + du)
                    },
                    log_string: function (str) {

                        // 1364 Object Pointer
                        // 1388 Länge 4 Bytes
                        // 1392 UTF-8 Zeichen 2 Bytes

                        console.log("log string : " + str);
                        for (i=str;i<str + 32;i++) {
                            var theChar = String.fromCharCode(teavm.memoryArray[i]);
                            console.log("entry " + i + " : " + theChar + " : " + teavm.memoryArray[i]);
                        }
                    }
                },
            }
            WebAssembly.compile(response).then(function(module) {
                teavm.module = module;
                teavm.instance = new WebAssembly.Instance(module, importObj);
                teavm.memory = teavm.instance.exports.memory;
                teavm.memoryArray = new Uint8Array(teavm.memory.buffer);
                console.log("Initialized")
                callback();
            });
        }
        xhr.send()
    }

    return TeaVM;
}();