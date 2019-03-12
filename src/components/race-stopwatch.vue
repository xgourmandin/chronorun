<script>
export default {
  props: {
    start: {
      type: Date,
      required: false,
      default: Date.now(),
    },
  },
  data() {
    return {
      now: Date.now(),
    }
  },
  computed: {
    elapsed: function() {
      return this.now - this.start.getTime()
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
  created: function() {
    var self = this
    setInterval(function() {
      self.$data.now = Date.now()
    }, 1000)
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
      return Math.floor(milli / chronoUnit.divider) % chronoUnit.maxValue
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
