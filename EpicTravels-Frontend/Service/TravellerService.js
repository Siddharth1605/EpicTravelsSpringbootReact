import axios from 'axios'

const URL = 'http://localhost:8080/traveller';

class TravellerService{

    getAllTravellers(){
        return axios.get(URL)
    }

    createTraveller(traveller){
        return axios.post(URL,traveller)
    }

    getTravellerById(travellerId){
        return axios.get(URL + '/' + travellerId);
    }

    updateTraveller(travellerId, traveller){
        return axios.put(URL + '/' +travellerId, traveller);
    }

    deleteTraveller(travellerId){
        return axios.delete(URL + '/' + travellerId);
    }
    deleteAllTraveller(travellerId){
        return axios.delete(URL);
    }
}

export default new TravellerService();