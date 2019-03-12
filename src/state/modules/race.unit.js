import * as raceModule from './race'

describe('@state/modules/race', () => {
  it('exports a valid Vuex module', () => {
    expect(raceModule).toBeAVuexModule()
  })
})
