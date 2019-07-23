function padNumber(number) {
  if (number.toString().length === 2) return number.toString()
  else return '0' + number.toString()
}

export {padNumber}