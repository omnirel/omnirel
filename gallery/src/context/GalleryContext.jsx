import axios from "axios";
import { createContext, useEffect, useState } from "react";

const GalleryContext = createContext()

const GalleryProvider = ({children}) =>{

    const [images, setImages] = useState([])

    async function getImages(){
        try{
            const response = await axios.get("/api/images")
            setImages(response.data)
        }catch(e){
            console.error("Hiba történt a képek lekérdezése során: ", e)
        }
    }

    async function addImage(url){
        try{
            const response = await axios.post("/api/image", {url})
            setImages(response.data)
        }catch(e){
            console.error("Hiba történt a kép elküldése során: ", e)
        }
    }

    async function rateImage(id, rating){
        try{
            const response = await axios.post("/api/rate", {id, rating})
            setImages(response.data)
        }catch(e){
            console.error("Hiba történt a kép értékelése során: ", e)
        }
    }

    useEffect(() =>{
        getImages()
    }, [])

    return (
        <GalleryContext.Provider value={{images, addImage, rateImage}}>
            {children}
        </GalleryContext.Provider>
    )

}

export {GalleryContext, GalleryProvider}