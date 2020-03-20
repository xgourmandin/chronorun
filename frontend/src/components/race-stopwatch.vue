<script>
import { mapState } from 'vuex'
import {padNumber} from "../utils/number-utils";

export default {
  data() {
    return {
      now: Date.now(),
      interval: null,
    }
  },
  computed: {
    ...mapState({
      start: (state) => state.race.raceStartDate,
      raceStarted: (state) => state.race.raceStarted,
    }),
    elapsed: function() {
      if (this.raceStarted) return this.now - this.start.getTime()
      else return 0
    },
    hour: function() {
      return padNumber(this.milliToHour(this.elapsed))
    },
    minute: function() {
      return padNumber(this.milliToMinute(this.elapsed))
    },
    second: function() {
      return padNumber(this.milliToSecond(this.elapsed))
    },
  },
  watch: {
    raceStarted: function(newVal) {
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
      this.interval = setInterval(function() {
        this.now = Date.now()
      }.bind(this), 1000)
    },
    stopStopwatch() {
      if (this.interval) {
       clearInterval( this.interval)
        this.interval = null
      }
    },
  },
}
</script>

<template>
  <div class="chrono">
    <p>
      {{ hour }}<span>H</span>{{ minute }}<span>M</span>{{ second }}<span>s</span>
    </p>
  </div>
</template>

<style scoped>
.chrono {
  width: 100%;
  text-align: center;
}

.chrono p {
  font-size: 7rem;
  color: #222222;
}

.chrono p span {
  margin: 0 1rem;
}
</style>
