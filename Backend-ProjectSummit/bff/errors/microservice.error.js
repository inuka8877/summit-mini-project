const morgan = require('morgan');

function handleMicroserviceError(error, res) {
    if (error.response) {
        // The microservice responded with a non-2xx status code
        console.error('Microservice error:', error.response.status, error.response.error);
        res.status(error.response.status).json({ error: 'Microservice Error', details: error.response.error });
        
    } else if (error.request) {
        // The request was made, but no response was received
        console.error('Microservice is unreachable:', error.message);
        res.status(500).json({ error: 'Microservice Unreachable' });
        
    } else {
        // Something happened in setting up the request that triggered an Error
        console.error('Unknown error:', error.message);
        res.status(500).json({ error: 'Unknown Error' });
        
    }
}

module.exports = { handleMicroserviceError };
