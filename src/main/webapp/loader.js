var TeaVM = function() {

    function TeaVM() {
        this.instance = null;
        this.module = null;
        this.memory = null;
        this.memoryInt32Array = null;
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

            var wasmMemory = new WebAssembly.Memory({initial: 2048});

            var importObj = {
                memory: wasmMemory,
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
                        var vm = teavm;
                        console.log("log string : " + str)
                    }
                },
            }
            WebAssembly.instantiate(response, importObj).then(function(results) {
                teavm.instance = results.instance;
                teavm.module = results.module;
                teavm.memory = wasmMemory;
                teavm.memoryInt32Array = new Uint32Array(wasmMemory.buffer);
                console.log("Initialized")
                callback();
            });
        }
        xhr.send()
    }

    return TeaVM;
}();