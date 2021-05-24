import authHeader, { BASE_URL, HTTP } from "../http";
import axios from "axios";

export default {
    allRentals() {
        return HTTP.get(BASE_URL + "/rentals", {headers: authHeader()}).then(
            (response) => {
                return response.data;
            }
        );
    },
    rent(rental){
        return axios({
            url: BASE_URL + '/rentals',
            method: 'Post',
            data: rental,
            headers: authHeader(),
            responseType: 'blob',
        }).then((response) => {
            var fileURL = window.URL.createObjectURL(new Blob([response.data]));
            var fileLink = document.createElement('a');

            fileLink.href = fileURL;
            fileLink.setAttribute('download', 'contract.pdf');
            document.body.appendChild(fileLink);

            fileLink.click();
        });
        /*return HTTP.post(BASE_URL + "/rentals", rental,{ headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );*/
    },
    delete(car){
        return HTTP.delete(BASE_URL + "/rentals/" + car.id, { headers: authHeader() }).then();
    },
};