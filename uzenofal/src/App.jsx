import './App.css'
import Message from './components/Message'
import { MessageProvider } from './context/MessageContext'

function App() {

  return (
    <MessageProvider>
      <Message></Message>
    </MessageProvider>
  ) 
}

export default App
