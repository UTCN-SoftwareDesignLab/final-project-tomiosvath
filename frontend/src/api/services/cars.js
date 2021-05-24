import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    allCars() {
        return HTTP.get(BASE_URL + "/cars", {headers: authHeader()}).then(
            (response) => {
                return response.data;
            }
        );
    },
    delete(car){
        return HTTP.delete(BASE_URL + "/cars/" + car.id, { headers: authHeader() }).then();
    },
    returnCar(car){
        return HTTP.post(BASE_URL + "/cars/return/" + car.id, {}, { headers: authHeader() });
    }
};