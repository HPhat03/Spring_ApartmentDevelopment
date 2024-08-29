
const CommonStyle = {
    mainBg : {
        backgroundColor: "#003b95",
        color: "white",
        fontFamily: "Baloo Paaji 2"
    },
    contactBar: {
        display: "flex", 
        justifyContent: "center", 
        marginTop: -30, 
        height: 60
    },
    Component: {
        margin: 75,
        width: "90%",
        height: 350,
        padding: 10,
        backgroundColor: "white",
        borderRadius: 10
    },
    Box: {
        borderColor: "#DFDFDF",
        borderStyle: "solid",
        borderWidth: 2,
        borderRadius: 5
    },
    OfferBox: {
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
        padding: 5,
        margin: 5
    },
    lifeStyle: {
        width: "33%", 
        height: 200, 
        backgroundColor: "red",     
        padding: 10,
        color: "white",
        borderRadius: 5,
        backgroundSize: "100%"
    },
    modal: {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 400,
        bgcolor: 'background.paper',
        border: '2px solid #000',
        boxShadow: 24,
        pt: 2,
        px: 4,
        pb: 3,
    },
    flex_space: {
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
    },
    flex_center: {
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
    },
    contentBlock: {
        width: "50%",
        height: "100%",
        flexDirection: "column",
        padding: 20
    }
}

export default CommonStyle