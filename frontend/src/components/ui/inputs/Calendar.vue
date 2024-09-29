<template>
    <v-menu ref="menu" :location="'bottom'" :close-on-content-click="false" :width="width" elevation="0" :eageer="false"
        :scroll-strategy="'close'">
        <template v-slot:activator="{ props }">
            <v-sheet v-bind="props" class="d-flex">
                <input type="text" :style="'width:' + width + '; '" :height="height" class="input" :value="label"
                    @input="updateValue" disabled placeholder="Wybierz daty">
                <img src="/calendar.svg" width="20" alt="" class="clickable"
                    :style="'position: absolute;margin-left:' + marginLeft + ';margin-top:' + marginTop + 'px;'">
            </v-sheet>
        </template>
        <v-sheet :width="width" elevation="0" class="calendar-menu" style="margin-top: -4px;border-radius: 0;">
            <div class="d-flex text-DarkBlue justify-space-around cmt-8">
                <v-icon @click="changeMonth(-1)">
                    mdi-chevron-left
                </v-icon>
                <div class=" fw-bold c-text-1 uppercase">
                    {{ monthName }}
                    {{ year }}
                </div>
                <v-icon @click="changeMonth(1)">
                    mdi-chevron-right
                </v-icon>
            </div>
            <v-sheet :height="260" :width="width">

                <div class="cmt-4">
                    <table class="calendar mx-auto text-center">
                        <thead class="header ">
                            <tr>
                                <th v-for="(header, index) in headers" :key="index">
                                    <v-sheet width="35" height="35" class="text-DarkBlue fw-bold c-text-5">
                                        {{ header }}
                                    </v-sheet>
                                </th>
                            </tr>
                        </thead>
                        <tbody class="body text-center">
                            <tr v-for="(week, index) in days" :key="index">
                                <td v-for="(day, dayIndex) in week" :key="dayIndex">
                                    <v-sheet v-if="day" :width="35" :height="35" @click="select(day.dayNumber)"
                                        :class="[isSelected(day.dayNumber) ? 'bg-LightBlue' : '', 'clickable rounded-circle d-flex justify-center align-center c-text-5 fw-bold text-NavyBlue']">
                                        {{ day?.dayNumber }}
                                    </v-sheet>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </v-sheet>
            <v-sheet :width="width" :height="40" color="DarkBlue" class="cmt-4 justify-center c-text-3 d-flex  clickable
align-center" @click="clear">
                <div>

                    WYCZYŚĆ
                </div>
            </v-sheet>
        </v-sheet>
    </v-menu>
</template>
<script>
export default {
    props: {
        startDate: {
            default: '',
        },
        endDate: {
            default: '',
        }
    },
    data() {
        return {
            defaultWidth: 370,
            defaultHeight: 464,
            defaultInputHeight: 64,
            monthNames: ["Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesień", "Październik", "Listopad", "Grudzień"],
            headers: ["PN", "WT", "ŚR", "CZ", "PT", "SO", "N"],
            monthIndex: 0,
            year: 0,
            date: new Date(),
        }
    },
    computed: {
        label() {
            var label = "";
            if (this.startDate) {
                label += "Od " + this.$moment(this.startDate).format('DD-MM-YY');
            }
            if (this.endDate) {
                label += " Do " + this.$moment(this.endDate).format('DD-MM-YY');
            }
            return label
        },
        multiplier() {
            return this.$store.getters.getMultiplier;
        },
        isXS() {
            return this.$store.getters.getIsXS
        },
        isMobile() {
            return this.$store.getters.getIsMobile
        },
        width() {
            return this.isXS ? "85vw" : this.multiplier * this.defaultWidth + "px"
        },
        height() {
            return this.multiplier * this.defaultHeight
        },
        monthName() {
            return this.monthNames[this.monthIndex]
        },
        inputHeight() {
            return this.multiplier * this.defaultInputHeight;
        },
        marginTop() {
            return this.inputHeight / 2 - 9;
        },
        marginLeft() {
            return this.isXS ? "calc(85vw - 30px)" : this.multiplier * this.defaultWidth - 35 + "px"
        },
    },
    methods: {
        clear() {
            this.$emit("update:startDate", null)
            this.$emit("update:endDate", null)
        },
        select(dayNumber) {
            var newDate = new Date(this.year, this.monthIndex, dayNumber);
            if (this.isSelected(dayNumber)) {
                this.unset(dayNumber)
                return;
            }
            if (!this.startDate) {
                this.change('startDate', newDate)
            } else {
                if (newDate < this.startDate) {
                    this.change('endDate', this.startDate)
                    this.change('startDate', newDate)
                } else {
                    this.change('endDate', newDate)
                }
            }
        },
        unset(dayNumber) {
            if (this.isEqual(this.startDate, dayNumber)) {
                this.change("startDate", null)
            } else if (this.isEqual(this.endDate, dayNumber)) {
                this.change("endDate", null)
            }
        },
        change(label, value) {
            this.$emit("update:" + label, value)
        },
        fixDates() {
            if (this.startDate && this.endDate && this.startDate > this.endDate) {
                var x = this.startDate;
                this.change('startDate', this.endDate)
                this.change('endDate', x)
            }
        },
        isEqual(date, dayNumber) {
            if (date) {
                return date.getFullYear() == this.year && date.getMonth() == this.monthIndex && date.getDate() == dayNumber;
            }
            return false;
        },
        isSelected(dayNumber) {
            return this.isEqual(this.startDate, dayNumber) || this.isEqual(this.endDate, dayNumber)
        },
        changeMonth(val) {
            this.monthIndex += val;
            var yearDiff = this.monthIndex < 0 ? -1 : (this.monthIndex >= 12 ? 1 : 0);
            this.monthIndex = (this.monthIndex + 12) % 12
            this.year += yearDiff
            this.getDaysOfMonth()
        },
        getDaysOfMonth() {
            var year = this.year;
            var month = this.monthIndex;
            const firstDayOfMonth = new Date(year, month, 1);

            const firstDayOfWeek = firstDayOfMonth.getDay();

            const lastDayOfMonth = new Date(year, month, 0);

            const lastDay = lastDayOfMonth.getDate();

            const daysFromPrevMonth = firstDayOfWeek === 0 ? 6 : firstDayOfWeek - 1;

            const totalDays = daysFromPrevMonth + lastDay;

            const totalWeeks = Math.ceil(totalDays / 7);

            const daysArray = [];
            let dayNumber = 1;
            for (let week = 0; week < totalWeeks; week++) {
                daysArray[week] = [];
                for (let day = 0; day < 7; day++) {
                    if ((week === 0 && day < daysFromPrevMonth) || dayNumber > lastDay) {
                        daysArray[week][day] = null;
                    } else {
                        daysArray[week][day] = { dayNumber: dayNumber, index: (week * 7 + day) };
                        dayNumber++;
                    }
                }
            }

            this.days = daysArray;
        },
        init() {
            this.monthIndex = this.date.getMonth()
            this.year = this.date.getFullYear()
            this.getDaysOfMonth()
        }
    },
    created() {
        this.init()
    },

}
</script>
<style scoped>
.calendar-menu {
    border: 1px #184773 solid;
    border-top: 0 !important;
    overflow: hidden !important;
}
</style>