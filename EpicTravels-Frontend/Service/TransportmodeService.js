import axios from 'axios'

const URL = 'http://localhost:8080/transportmode';

class TransportmodeService{

    getAllTransportmode(){
        return axios.get(URL)
    }

}

export default new TransportmodeService();