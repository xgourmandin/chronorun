import axios from 'axios'

export default() => {
  axios.defaults.headers.common['Access-Control-Allow-Origin'] = "Allow";
  return axios.create({
    baseURL: `api`,
    withCredentials: false,
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'Allow'
    }
  })
}