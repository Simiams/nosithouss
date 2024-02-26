const { app, BrowserWindow, Menu } = require('electron');

function createWindow() {
    const win = new BrowserWindow({
        width: 540,
        height: 960,
        webPreferences: {
            nodeIntegration: true,
            contextIsolation: false,
        }
    });


    const contextMenu = Menu.buildFromTemplate([
        { label: 'Inspecter', click: () => { win.webContents.openDevTools(); } }
    ]);

    win.webContents.on('context-menu', (e, params) => {
        contextMenu.popup(win, params.x, params.y);
    });

    win.setMenu(null);
    win.loadURL('http://localhost');
}


app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') {
        app.quit();
    }
});

app.on('activate', () => {
    if (BrowserWindow.getAllWindows().length === 0) {
        createWindow();
    }
});

app.whenReady().then(createWindow);
