<script>
import { mapState } from 'vuex'

export default {
  data() {
    return {
      now: Date.now(),
      interval: null,
    }
  },
  computed: {
    ...mapState('race', {
      start: (state) => state.raceStartDate,
      raceStarted: (state) => state.raceStarted,
    }),
    elapsed: function() {
      if (this.raceStarted) return this.now - this.start.getTime()
      else return 0
    },
    hour: function() {
      return this.padNumber(this.milliToHour(this.elapsed))
    },
    minute: function() {
      return this.padNumber(this.milliToMinute(this.elapsed))
    },
    second: function() {
      return this.padNumber(this.milliToSecond(this.elapsed))
    },
  },
  watch: {
    raceStarted: function(newVal, oldVal) {
      if (newVal) {
        this.startStopwatch()
      } else {
        this.stopStopwatch()
      }
    },
  },
  created: function() {
    if (this.raceStarted) {
      this.startStopwatch()
    }
  },
  methods: {
    padNumber: function(number) {
      if (number.toString().length === 2) return number.toString()
      else return '0' + number.toString()
    },
    milliToHour: function(milli) {
      return this.milliToChrono(milli, {
        divider: 1000 * 60 * 60,
        maxValue: 24,
      })
    },
    milliToMinute: function(milli) {
      return this.milliToChrono(milli, { divider: 1000 * 60, maxValue: 60 })
    },
    milliToSecond: function(milli) {
      return this.milliToChrono(milli, { divider: 1000, maxValue: 60 })
    },
    milliToChrono: function(milli, chronoUnit) {
      return Math.max(
        0,
        Math.floor(milli / chronoUnit.divider) % chronoUnit.maxValue
      )
    },
    startStopwatch() {
      let self = this
      this.interval = setInterval(function() {
        self.$data.now = Date.now()
      }, 1000)
    },
    stopStopwatch() {
      this.interval.clearInterval()
    },
  },
}
</script>

<template>
  <div :class="$style.chrono">
    <p
      >{{ hour }}<span>H</span>{{ minute }}<span>M</span>{{ second
      }}<span>s</span></p
    >
  </div>
</template>

<style lang="scss" module>
@import '@design';
.chrono {
  width: 100%;
  text-align: center;
}

.chrono p {
  font-size: 3rem;
  color: $color-text;
}

.chrono p span {
  margin: 0 1rem;
}
</style>
