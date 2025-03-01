import { group, check, fail } from 'k6';
import http from 'k6/http';
import { describe } from 'https://jslib.k6.io/expect/0.0.4/index.js';

export const options = {
    scenarios: {
        my_scenario: {
            executor: 'per-vu-iterations',
            vus: 10,
            iterations: 1,
            maxDuration: '1m',
        },
    },
}

export default function() {
    group('GET requests for time', function () {
        describe('Getting time', function () {
            const res = http.get("http://localhost:8080/server/current-time");
            if(!check(res, {
                'is status 200': (r) => r.status === 200,
            })){
                fail('Failed to get 200 response code on GET ONE, instead status code is: ' + res.status);
            }
            console.log(res.body);
        });
    });
}