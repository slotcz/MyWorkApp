package cz.o2.tvs.stb;

import cz.o2.tvs.accessory.Version;
import cz.o2.tvs.accessory.VersionValidator;
import cz.o2.tvs.db.Category;
import cz.o2.tvs.db.CurrentStbVersionFw;
import cz.o2.tvs.db.ImageStb;
import cz.o2.tvs.db.Model;
import cz.o2.tvs.db.NG_STB_ServiceFacade;
import cz.o2.tvs.db.RegisterFw;
import cz.o2.tvs.db.Stb;
import cz.o2.tvs.stb.servlet.RequestSTB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CheckerVersion {


    private VersionValidator versionValidator;

    private List<CurrentStbVersionFw> listSTBs;
    private List<RegisterFw> listRFW;
    private List<Model> listModel;
    private List<ImageStb> listImageStb;
    private List<Category> listCategory;

    private Map mapSTB = new HashMap<Integer, Stb>();

    private Map mapSTBbyMAC = new HashMap<String, Stb>();

    private Map imgMap = new HashMap<String, Integer>();

    //private Map catMAC = new HashMap<String, Integer>();

    private CurrentStbVersionFw[][][] versFW;

    private int countSTB;

    NG_STB_ServiceFacade srvcFacade = new NG_STB_ServiceFacade();

    Logger LOG = Logger.getLogger(RequestSTB.class.getName());


    public CheckerVersion() {
        super();
        versionValidator = new VersionValidator();


        listSTBs = srvcFacade.getCurrentStbVersionFwFindAll();
        /*
        for (CurrentStbVersionFw curr : listSTBs) {
            catMAC.put(curr.getMac(), curr.getCategory());
        }
*/
        listRFW = srvcFacade.getRegisterFwFindAll();

        listModel = srvcFacade.getModelFindAll();

        listImageStb = srvcFacade.getImageStbFindAll();
        for (ImageStb img : listImageStb) {
            imgMap.put(img.getName(), img.getId());
        }

        listCategory = srvcFacade.getCategoryFindAll();

        LOG.info("stbs=" + listSTBs.size() + " rfw=" + listRFW.size() + " model=" + listModel.size()); //
        countSTB = srvcFacade.getCountSTBbyMAC();
        versFW = new CurrentStbVersionFw[countSTB][listCategory.size() + 1][listImageStb.size() + 1];

        listSTBs.forEach(stb -> LOG.info(stb.toString()));
        int iMAC = 0;

        for (CurrentStbVersionFw itemSTB : listSTBs) {

            versFW[iMAC][itemSTB.getCategory()][itemSTB.getImage()] = itemSTB;
            LOG.info(" i=" + iMAC + " j=" + itemSTB.getImage() + " k=" + itemSTB.getCategory() + " ***** " + itemSTB.toString());
            //TODO ERROR ?? if (itemSTB.getImage() == 3)
            iMAC++;
        }

        for (int i = 0; i < countSTB; i++) {
            for (int j = 0; j <= listCategory.size(); j++) {
                for (int k = 0; k <= listImageStb.size(); k++) {
                    if (versFW[i][j][k] != null) {
                        LOG.info(" i=" + i + " j=" + j + " k=" + k + " %%%%% " + versFW[i][j][k].toString());
                    }
                }
            }
        }

        mapSTB = srvcFacade.getMapSTB();
        mapSTBbyMAC = srvcFacade.getMapSTBByMAC();
    }

    private int verifyVersion(String varFWversion, String fwVersion) { // 1 from strb ; 2 from DB
        int result = -1;
        Version version = new Version(varFWversion);
        if (version.compareTo(fwVersion) > 0) {
            result = 1;
        }
        return result;
    }

    public java.lang.String getNewVersion(java.lang.String varMAC, java.lang.String pathImage, java.lang.String varFWversion) {

        java.lang.String result = "";

        LOG.info(" * * *  pathImage=" + pathImage + " for NAC=" + varMAC);

        if (!varFWversion.isEmpty() && !versionValidator.validate(varFWversion)) {
            LOG.warning("Bad form at version=" + varFWversion + " for NAC=" + varMAC);
            varFWversion = "";
        }

        int j = (Integer) imgMap.get(pathImage.trim()); // image
        //int j = (Integer) catMAC.get(varMAC);
        
        Stb stb = (Stb) mapSTBbyMAC.get(varMAC); // category
        int k = stb.getCategory();
        
        /*
        if(mapSTBbyMAC.containsKey(varMAC)){

        }else{

        }
        */
        LOG.info("pathImage=" + pathImage + " k=" + k + " j=" + j + " for NAC=" + varMAC);
        for (int i = 0; i < countSTB; i++) {
            //TODO for debug only
          /*
            if (versFW[i][j][k] != null) {
                if (versFW[i][j][k].getMac().equals(varMAC)) {
                    result = versFW[i][j][k].getVersion().toString();
                    LOG.info("[i][j][k]" + i + " " + j + " " + k);
                  if (!varFWversion.isEmpty()) {
                        LOG.info("result=" + result + "*endResult*");
                        if (verifyVersion(varFWversion, result) <= 0) {
                            result = "";
                        }
                    }
                    // Stb stb = (Stb) mapSTB.get(versFW[i][j][k].getId());
                    stb.setVersionFw(versFW[i][j][k].getId_version());
                    updateVersionStb(stb);
                    break;
                }
                
            } else
*/ { // havn't info last version into register stb, take last version from register_fm
                RegisterFw regFw = srvcFacade.getLastVersionFwByCategory(k, j);
                stb.setVersionFw(regFw.getId());
                updateVersionStb(stb);
                result = regFw.getVersion().toString();
                break;
            }
        }

        return result;
    }

    public String getcodePath(String varFWversion, String varMAC) {
        String result = "";
        int category = ((Stb) mapSTBbyMAC.get(varMAC)).getCategory();
        for (RegisterFw rfw : listRFW) {
            if (rfw.getVersion().equals(varFWversion) && rfw.getCategory() == category) {
                result = rfw.getCodePatch();
            }
        }
        return result;
    }

    private void updateVersionStb(Stb stb) {
        srvcFacade.updateSTB(stb);
        LOG.info("AFTER updateVersionStb MAC=" + stb.getMac());
    }

    private void writeHistory(Stb stb) {
        //TODO zdes prodolzhit History history = new History(stb.getId(),stb.getVersionFw());
    }

    public boolean checkMAC(String varMAC) {
        boolean result = false;
        if (mapSTBbyMAC.containsKey(varMAC)) {
            Stb stb = ((Stb) mapSTBbyMAC.get(varMAC));
            if (stb.getCategory() != 3) {
                result = true;
            }
        }

        return result;
    }
}
