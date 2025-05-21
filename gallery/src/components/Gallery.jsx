import { useContext, useState } from "react";
import { GalleryContext } from "../context/GalleryContext";
import style from "./Gallery.module.css"
import TextField from '@mui/material/TextField';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Rating from '@mui/material/Rating';

export default function Gallery() {

    const { images, addImage, rateImage } = useContext(GalleryContext)
    const [newUrl, setNewUrl] = useState("")

    const handleUrlChange = (e) => {
        setNewUrl(e.target.value)
    }

    async function handleAddImage() {
        const trimmedUrl = newUrl.trim()
        if (!trimmedUrl) {
            alert("Adj meg egy URL-t!")
            return
        }
        try {
            await addImage(newUrl)
            setNewUrl("")
        } catch (e) {
            console.error(e)
        }
    }

    async function handleRate(id, rating) {
        try {
            await rateImage(id, rating)
        } catch (e) {
            console.error(e)
        }
    }

    return (
        <>
            <div className={style.container}>
                <Typography gutterBottom variant="h5" component="div">
                    Galéria
                </Typography>
                <TextField id="outlined-basic" label="Kép URL" variant="outlined" value={newUrl} onChange={handleUrlChange} fullWidth/>
                <Button variant="contained" onClick={handleAddImage}>Küldés</Button>

            </div>
            <div className={style.mediaCards}>
                {images.map((image, index) => {
                    return (
                        <Card key={image.id} sx={{ maxWidth: 345, transition: "transform 0.5s ease-in-out", "&:hover": {transform: "scale(1.1)"} }}>
                            <CardMedia
                                sx={{ height: 160, width: 160 }}
                                image={image.url}
                                title={`Kép {${image.id}}`}
                            />
                            <CardContent className={style.rating}>
                                <Rating
                                    name="simple-controlled"
                                    value={image.rating}
                                    onChange={(event, newRating) => {
                                        handleRate(image.id, newRating);
                                    }}
                                    
                                />
                            </CardContent>
                        </Card>
                    )
                })}
            </div>

        </>
    )

}