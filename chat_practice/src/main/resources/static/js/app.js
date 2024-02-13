/**
 * app.js
 */


document.addEventListener('DOMContentLoaded', function() {
    
    const userPage = document.querySelector('.username');
    const chatPage = document.querySelector('.chat');
    const usernameForm = document.querySelector('.username-form');
    const messageForm = document.querySelector('.message-form');
    const messageInput = document.querySelector('.message-input');
    const messageArea = document.querySelector('.message-area');
    const connectingElement = document.querySelector('.connecting');
    
    
    let stompClient = null;
    let username = null;
    
    function connect (event) {
        console.log(messageForm);
        
        username = document.querySelector('.username-input').value;
        console.log(document.querySelector('.username'));
        console.log(username);
        
        event.preventDefault();
        
        if (username) {
            userPage.classList.add('d-none');
            chatPage.classList.remove('d-none');
            
            let socket = new SockJS('/chat'); // config에서 정한 endpoint
            stompClient = Stomp.over(socket);
            
            stompClient.connect({}, onConnect, onError);
        }
        
        
    }
    
    
    function onConnect() {
        // subscribe함..
        stompClient.subscribe('/sub/chatroom', onMessageReceived);
        
        // 서버에 username알려줌.. send(목적지, header정보, body정보);
        stompClient.send('/pub/chat.addUser', {}, JSON.stringify({sender: username, type: "JOIN", testField: "오나이거"}));
        
        connectingElement.classList.add('d-none');
        
    }
    
    function onError(error) {
        connectingElement.textContent = '에러발생.. 새로고침 하세요.' + error;
        connectingElement.style.color = 'red';
    }
    
    function sendMessage(event) {
        const messageContent = messageInput.value.trim();
        if (messageContent && stompClient) {
            
            const chatMessage = {sender: username, content: messageInput.value, type:'CHAT'};
            
            stompClient.send("/pub/chat.sendMessage", {}, JSON.stringify(chatMessage));   // 아규먼트 목적지, header정보, body정보
            messageInput.value = '';
        }
        
        event.preventDefault();
    }
    
    function onMessageReceived(payload) {
        
        const message = JSON.parse(payload.body);
        
        console.log(`페이로드: ${payload}`);
        console.dir(payload);
        
        const messageElement = document.createElement('div');
        
        if (message.type === 'JOIN') {
            messageElement.classList.add('event-message');
            message.content = message.sender + ' joined!';
        } else if (message.type === 'LEAVE') {
            messageElement.classList.add('event-message');
            message.content = message.sender + ' left!';
        } else {
            messageElement.classList.add('chat-message');
            
            const usernameElement = document.createElement('span');
            const usernameText = document.createTextNode(message.sender);
            
            usernameElement.appendChild(usernameText);
            messageElement.appendChild(usernameElement);            
        }
        const textElement = document.createElement('p');
        console.log(message.content);
        const messageText = document.createTextNode(message.content);
        textElement.appendChild(messageText);
        
        messageElement.appendChild(textElement);
        
        messageArea.appendChild(messageElement);
        // 테스트
        console.log(messageElement);
        console.log(messageArea);
        // 테스트 끝        
        
        messageArea.scrollTop = messageArea.scrollHeight;
        
    }
    
    
    
    
    usernameForm.addEventListener('submit', connect);
    messageForm.addEventListener('submit', sendMessage);
    
    
});