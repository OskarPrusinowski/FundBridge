<template>
    <div>
        <QuillEditor :modules="modules" ref="MainQuil" toolbar="full" @update:content="change" />
    </div>
</template>
<script>
import QuillBetterTable from 'quill-better-table'
import BlotFormatter from 'quill-blot-formatter'
import ImageCompress from "quill-image-compress";
export default {
    data() {
        return {
            modules: [
                {
                    name: "ImageCompress",
                    module: ImageCompress,
                    options: {
                        quality: 0.7,
                        maxWidth: 1000,
                        maxHeight: 1000,
                        imageType: "image/jpeg",
                        debug: true,
                        suppressErrorLogging: false,
                        insertIntoEditor: undefined,
                    },
                },
                {
                    name: "BlotFormatter",
                    module: BlotFormatter,
                    options: {},
                },
            ]
        };
    },
    props: {
        label: { String, default: "" },
    },
    data() {
        return {
            quillValue: "",
        }
    },
    methods: {
        change(value) {
            this.quillValue = value.ops
            this.changed()
        },
        changed() {
            this.$emit("changed", this.quillValue)

        },
        async get() {
            await this.getInputtedFiles();
            await this.$store.dispatch("uploadFiles");
            var content = await this.getContent();
            this.$emit("get", content)
            return content;
        },
        async getInputtedFiles() {
            var files = [];
            for (let i = 0; i < this.quillValue.length; i++) {
                if (this.quillValue[i].insert && this.quillValue[i].insert.hasOwnProperty('image')) {
                    var url = this.quillValue[i].insert.image;
                    var mimeType = url.split(':')[1].split(';')[0].split('/')[1];
                    var name = (new Date().getTime()).toString() + "_" + i + "." + mimeType;
                    var fileInfo = { url: url, name: name };
                    var file = await this.getFileFromUrl(fileInfo.url, fileInfo.name);
                    files.push(file)
                }
            }
            this.$store.commit("setFiles", files);
        },
        async getContent() {
            var content = [];
            var fileIndex = 0;
            var filesInfos = this.$store.getters.getFilesInfos;
            for (let i = 0; i < this.quillValue.length; i++) {
                var currentContent = this.quillValue[i];
                if (currentContent.insert && currentContent.insert.hasOwnProperty('image')) {
                    var filePath = "http://localhost:8080/api/files?path=" + filesInfos[fileIndex].path;
                    currentContent.insert.image = filePath;
                    fileIndex++;
                }
                content.push(currentContent);
            }
            return content;
        },
        async getFileFromUrl(url, filename) {
            return await fetch(url)
                .then(response => response.blob())
                .then(blob => {
                    return new File([blob], filename);
                });
        },
        set(value) {
            var oldDelta = this.$refs.MainQuil.getContents();
            if (oldDelta.ops.length == 0) {
            } else if (oldDelta.ops.length == 1 && oldDelta.ops[0].insert == "\n") {
                oldDelta.ops = []
            } else {
                return;
            }
            if (value && value.length != 0) {
                var newDelta = JSON.parse(value)
                newDelta.forEach(element => {
                    oldDelta.ops.push(element);
                })
                if (newDelta.length == 0) {
                    oldDelta.ops.push({
                        "insert": " "
                    })
                }
                this.$refs.MainQuil.setContents(oldDelta)
            } else {
                this.$refs.MainQuil.setContents([])
            }
            this.$emit("changed", this.quillValue)
        }
    }

}
</script>