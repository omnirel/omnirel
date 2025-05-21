// App.jsx

import Gallery from "./components/Gallery";
import { GalleryProvider } from "./context/GalleryContext";



function App() {
 
  return (
      <GalleryProvider>
        <Gallery></Gallery>
      </GalleryProvider>
  );
}

export default App;
