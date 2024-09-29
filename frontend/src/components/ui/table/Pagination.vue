<template>
    <div class="d-flex justify-center">
        <v-icon @click="update(-1)">
            mdi-chevron-left
        </v-icon>
        <div class="pages d-flex ">
            <div v-for="(page, index) in pages" class="mx-2 clickable "
                :class="[page == this.page ? 'text-DarkerBlue' : 'text-grey']" :key="index" @click="change(page)">
                {{ page }}</div>
        </div>
        <v-icon @click="update(1)">
            mdi-chevron-right
        </v-icon>
    </div>
</template>
<script>
export default {
    props: {
        totalPages: {
            default: 1
        },
    },
    data() {
        return {
            page: 1,
            totlaVisible: 9
        }
    },
    computed: {
        pages() {
            const range = 7;
            const middle = Math.ceil(range / 2);
            const start = Math.max(1, this.page - middle + 1);
            const end = Math.min(start + range - 1, this.totalPages);
            return Array.from({ length: end - start + 1 }, (_, i) => start + i);
        }
    },
    methods: {
        updated(page) {
            this.set(page)
            this.$emit("updated", page)
        },
        set(page) {
            this.page = page;
        },
        change(val) {
            this.set(val);
            this.$emit("updated", val)
        },
        update(val) {
            var newVal = this.page + val;
            if (newVal < 1) {
                newVal = 1;
            }
            if (newVal >= this.totalPages) {
                newVal = this.totalPages;
            }
            this.change(newVal)
        }
    }
}
</script>