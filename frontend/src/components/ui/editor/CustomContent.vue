<template>
    <div>
        <QuillEditor ref="MainQuil" :readOnly="true" />
    </div>
</template>
<script>
export default {
    data() {
        return {
            quillValue: "",
        }
    },
    methods: {
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
        }
    }
}
</script>