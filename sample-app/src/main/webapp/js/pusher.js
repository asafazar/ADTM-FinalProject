/**
var Pusher = require('pusher');

var pusher = new Pusher({
    appId: '185377',
    key: '6ddefb4dd81db7c72a0d',
    secret: '3f69261d7626161b410e',
    encrypted: true
});

pusher.trigger('test_channel', 'my_event', {
    "message": "hello world"
});
 */